package com.tobery.personalmusic.music

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.FloatRange
import androidx.lifecycle.LifecycleOwner
import com.lzx.starrysky.OnPlayProgressListener
import com.lzx.starrysky.SongInfo
import com.lzx.starrysky.StarrySky
import com.lzx.starrysky.control.RepeatMode
import com.lzx.starrysky.notification.NotificationConfig
import com.tobery.personalmusic.music.SpConstant.REPEAT_MODE_NONE
import java.io.File

object MusicPlay {

    val notificationConfig = NotificationConfig.create {
        targetClass { "com.tobery.personalmusic.music.NotificationReceiver" }
        targetClassBundle {
            val bundle = Bundle()
            bundle.putString("title", "我是点击通知栏转跳带的参数")
            bundle.putString("targetClass", "com.tobery.personalmusic.ui.song.CurrentSongPlayActivity")
            //参数自带当前音频播放信息，不用自己传
            return@targetClassBundle bundle
        }
        pendingIntentMode { NotificationConfig.MODE_BROADCAST }
    }

    @JvmStatic
    fun initConfig(context: Context,config: PlayConfig = PlayConfig()){
       val cacheFile = File.createTempFile(config.cacheFilePath,null,context.cacheDir)
       StarrySky.init(context.applicationContext as Application)
           .apply {
               setOpenCache(config.isOpenCache)
               setCacheDestFileDir(cacheFile.absolutePath)
               setCacheMaxBytes(config.cacheByteNum)
               setAutoManagerFocus(config.isAutoManagerFocus)
               setImageLoader(config.defaultImageLoader)
               //addInterceptor(config.defaultPermissionIntercept)
               setNotificationSwitch(config.defaultNotificationSwitch)
               setNotificationType(config.notificationType)
               setNotificationConfig(notificationConfig)
           }
           .apply()
    }

    //进度监听
    @JvmStatic
    fun onPlayProgressListener(callback: OnMusicPlayProgressListener? = null){
        StarrySky.with().setOnPlayProgressListener(object : OnPlayProgressListener{
            override fun onPlayProgress(currPos: Long, duration: Long) {
                callback?.onPlayProgress(currPos,duration)
            }
        })
    }

    //状态监听
    @JvmStatic
    fun onPlayStateListener(owner: LifecycleOwner, callback: OnMusicPlayStateListener? = null){
        StarrySky.with().playbackState().observe(owner){
            callback?.onPlayState(it)
        }
    }

    //移动进度
    @JvmStatic
    fun seekTo(progress: Long){
        StarrySky.with().seekTo(progress,true)
    }

    //获取速度 1为正常播放
    @JvmStatic
    fun getPlaySpeed():Int = StarrySky.with().getPlaybackSpeed().toInt()

    //改变语速
    @JvmStatic
    fun changeSpeed(multiple: Float){
        StarrySky.with().onDerailleur(false,multiple)
    }

    //设置音量
    @JvmStatic
    fun setVolume(@FloatRange(from = 0.0,to = 1.0) volume: Float){
        StarrySky.with().setVolume(volume)
    }

    //设置播放模式
    @JvmStatic
    fun setRepeatMode(mode: Int = REPEAT_MODE_NONE,isLoop: Boolean = false){
        StarrySky.with().setRepeatMode(mode,isLoop)
    }

    //获取播放模式
    @JvmStatic
    fun getRepeatMode(): Int = StarrySky.with().getRepeatMode().repeatMode

    //下一首
    @JvmStatic
    fun skipToNext(){
        StarrySky.with().skipToNext()
    }

    //上一首
    @JvmStatic
    fun skipToPrevious(){
        StarrySky.with().skipToPrevious()
    }

    //播放 by id
    @JvmStatic
    fun playMusicById(songId: String?){
        StarrySky.with().playMusicById(songId)
    }

    //播放 by url
    @JvmStatic
    fun playMusicByUrl(songUrl: String){
        StarrySky.with().playMusicByUrl(songUrl)
    }

    //播放 by SongInfo
    @JvmStatic
    fun playMusicByInfo(info: SongInfo?){
        StarrySky.with().playMusicByInfo(info)
    }

    //播放列表以及对应歌曲的下标
    @JvmStatic
    fun playMusicByList(mediaList: MutableList<SongInfo>,index: Int = 0){
        StarrySky.with().playMusic(mediaList,index)
    }

    //暂停
    @JvmStatic
    fun pauseMusic(){
        if (StarrySky.with().isPlaying()){
            StarrySky.with().pauseMusic()
        }
    }

    //恢复播放
    @JvmStatic
    fun restoreMusic(){
        if (StarrySky.with().isPaused()){
            StarrySky.with().restoreMusic()
        }
    }

    //重播
    @JvmStatic
    fun replayCurrMusic(){
        StarrySky.with().replayCurrMusic()
    }

    //停止播放
    @JvmStatic
    fun stopMusic(){
        StarrySky.with().stopMusic()
    }

    //当前传入music id 是否播放
    @JvmStatic
    fun isCurrMusicIsPlaying(songId: String?){
        StarrySky.with().isCurrMusicIsPlaying(songId)
    }

    //当前传入music id 是否暂停
    @JvmStatic
    fun isCurrMusicIsPaused(songId: String?){
        StarrySky.with().isCurrMusicIsPaused(songId)
    }

    //获取音乐长度
    @JvmStatic
    fun getDuration():Long = StarrySky.with().getDuration()

    //是否正在播放
    @JvmStatic
    fun isPlaying():Boolean = StarrySky.with().isPlaying()

    //获取当前播放音乐信息
    @JvmStatic
    fun getNowPlayingSongInfo(): SongInfo? = StarrySky.with().getNowPlayingSongInfo()

    //获取播放队列
    @JvmStatic
    fun getPlayList():MutableList<SongInfo> = StarrySky.with().getPlayList()

    //更新播放列表
    @JvmStatic
    fun updatePlayList(list: MutableList<SongInfo>){
        StarrySky.with().updatePlayList(list)
    }

    //根据id移除
    @JvmStatic
    fun removeSongInfoById(songId: String?){
        StarrySky.with().removeSongInfo(songId)
    }

    //关闭notification
    @JvmStatic
    fun closeNotification(){
        StarrySky.closeNotification()
    }

    //打开notification
    @JvmStatic
    fun openNotification(){
        StarrySky.openNotification()
    }

}