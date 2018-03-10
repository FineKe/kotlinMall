package com.litesky.user.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.utils.UserPrefsUtils
import com.litesky.base.common.BaseConstant
import com.litesky.base.ext.onClick
import com.litesky.base.ui.activity.BaseMvpActivity
import com.litesky.user.R
import com.litesky.user.data.UserInfo
import com.litesky.user.di.component.DaggerUserComponent
import com.litesky.user.presenter.UserInfoPresenter
import com.litesky.user.presenter.view.UserInfoView
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

/**
 * Created by finefine.com on 2018/3/10.
 */
class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(),UserInfoView,TakePhoto.TakeResultListener{
    private lateinit var mTakePhoto:TakePhoto

    private lateinit var mTempFile:File

    private val mUploadManager: UploadManager by lazy {
        UploadManager()
    }

    private  var mLocalFile:String? = null

    private var mRemoteFile:String? = null

    private var mUserIcon : String? = null

    private var mUserName:String? = null
    private var mUserSign:String? = null
    private var mUserGender:String? = null
    private var mUserMobile:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this,this)
        initView()
        mTakePhoto.onCreate(savedInstanceState)
        initData()
    }


    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)


        mRemoteFile = mUserIcon
        mUserMobileTv.setText(mUserMobile)
        if (mUserIcon != "") {

            GlideUtils.loadUrlImage(this,mUserIcon!!,mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)


    }

    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFile!!,mUserNameEt.text?.toString()?:"",if (mGenderMaleRb.isChecked) "0" else "1",mUserSignEt.text.toString()?:"")
        }
    }

    private fun showAlertView() {
        AlertView("选择图片","","取消",null, arrayOf("拍照","相册"),
                this,AlertView.Style.ActionSheet,object :OnItemClickListener{

            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
                when (position) {
                    0 -> {
                        createTempFile()
                        if (ContextCompat.checkSelfPermission(this@UserInfoActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED||
                                ContextCompat.checkSelfPermission(this@UserInfoActivity,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED||
                                ContextCompat.checkSelfPermission(this@UserInfoActivity,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        android.Manifest.permission.READ_EXTERNAL_STORAGE), 2)
                            }
                        } else {
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                       }
                    1 -> {mTakePhoto.onPickFromGallery()}
                }
            }
        }).show()
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto",result?.image?.originalPath)
        Log.d("TakePhoto",result?.image?.compressPath)
        mLocalFile = result?.image?.compressPath
        mPresenter.getUploadToken()

    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d("erroro",msg)
    }

    override fun daggerInject() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode,data)
    }


    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(),tempFileName)
            return
        }
        this.mTempFile = File(filesDir,tempFileName)
    }

    override fun onEditUserInfo(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFile,null,result,object :UpCompletionHandler{
            /**
             * 用户自定义的内容上传完成后处理动作必须实现的方法
             * 建议用户自己处理异常。若未处理，抛出的异常被直接丢弃。
             *
             * @param key      文件上传保存名称
             * @param info     上传完成返回日志信息
             * @param response 上传完成的回复内容
             */
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject) {
                mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS+response.get("hash")
                GlideUtils.loadUrlImage(this@UserInfoActivity,mRemoteFile!!,mUserIconIv)
            }

        },null)
    }
}
