package dev.yjyoon.kwnotice.domain.model

enum class FcmTopic(val value: String) {
    KwHomeNew(value = TOPIC_KW_HOME_NEW),
    KwHomeEdit(value = TOPIC_KW_HOME_EDIT),
    SwCentralNew(value = TOPIC_SW_CENTRAL_NEW),
    KwDormCommon(value = TOPIC_KW_DORM_COMMON),
    KwDormRecruitment(value = TOPIC_KW_DORM_RECRUITMENT)
}

private const val TOPIC_KW_HOME_NEW = "kw-home-new"
private const val TOPIC_KW_HOME_EDIT = "kw-home-edit"
private const val TOPIC_SW_CENTRAL_NEW = "sw-central-new"
private const val TOPIC_KW_DORM_COMMON = "kw-dorm-common"
private const val TOPIC_KW_DORM_RECRUITMENT = "kw-dorm-recruitment"
