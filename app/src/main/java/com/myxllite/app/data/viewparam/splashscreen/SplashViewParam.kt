package com.myxllite.app.data.viewparam.splashscreen

import com.myxllite.app.data.entity.splashscreen.SplashEntity

/**
 * Created by Bacharudin Farisi on 30/03/22
 */
data class SplashViewParam(
    var name:String,
    var job:String,
    var position:Int
){
    constructor(splashEntity: SplashEntity?):this(
        name = splashEntity?.name.orEmpty(),
        job = splashEntity?.job.orEmpty(),
        position = splashEntity?.position ?: 0
    )
}
