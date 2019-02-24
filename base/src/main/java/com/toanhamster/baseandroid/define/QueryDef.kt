package com.toanhamster.baseandroid.define

/**
 * Toan.IT
 * Created by vantoan on 24/2/19.
 * Email: Huynhvantoan.itc@gmail.com
 */

object QueryDef {
    //const val GET_ASSETS          = "?\$expand=resources(url),genres,epg,series_seasons,resources&\$orderby=lcn&categories.id[eq]="
    const val GET_LIST_ASSETS     = "?\$expand=*&\$orderby=lcn&categories.id[eq]="
    const val GET_LIST_CHANNEL    = "?type[eq]=channel&\$expand=*&\$orderby=lcn"
    const val GET_LIST_MOVIES     = "?type[eq]=movie&\$expand=*&\$orderby=lcn"
    const val GET_MOVIES          = "?type[eq]=movie&category.id[eq]="
    const val GET_ASSETS          = "/{asset_id}?\$expand=bookmark"
    const val GET_PROGRAM         = "/{program_id}?\$expand=*"
    //const val GET_EPG             = "?\$orderby=lcn&\$expand=*&&live.id[in]={channelID}&start_time[gt]={dayTimeStart}T23:59:59Z&end_time[lt]={dayTimeEnd}T00:00:01Z"
    const val GET_EPG             = "?\$orderby=lcn&\$expand=program&live.id[in]={channelID}&start_time[lt]={dayTimeStart}&end_time[gt]={dayTimeEnd}"
    const val GET_INFO_CHANNEL    = "?live.id[eq]={channelID}&start_time[gt]={dayTimeStart}&\$expand=program&\$offset=0&\$limit=2"
    const val GET_FAVORITE        = "?favorite[eq]=true&\$expand=*"
    const val GET_TV_SEASION      = "?season.id[in]={season_id}&\$expand=*"
    const val ADD_FAVORITE        = "/{asset_id}/commands/add-to-favorites"
    const val REMOVE_FAVORITE     = "/{asset_id}/commands/remove-from-favorites"
    const val GET_LINK_STREAM     = "/{asset_id}/resources/{resource_id}/url"
    const val GET_LINK_EPG        = "/{program_id}/catchup-url"
    const val GET_DEVICE_SETTING  = "/me?\$expand=*"
    const val GET_UPDATE_SETTING  = "/{id}"
    const val GET_LIST_DVR        = "?\$expand=*&state[in]=recorded,scheduled,recording"
    const val GET_DVR_REMOVE      = "/{eq}"
    const val GET_DVR_URL         = "/{eq}/url"
    const val GET_PACKAGE         = "{}"
    const val GET_PROFILE_CUSTOMER= "{\"email\":{email}}"
    const val GET_PROFILE_PAYMENT = "{\"id\":{clientid}}"
    const val GET_PAYMENT_HISTORY = "{\"action\":\"GetCredits\",\"clientid\":{clientid}}"
    const val SEND_TIME_PLAY      = "/{asset_id}/commands/add-bookmark?position={seed}"
}
