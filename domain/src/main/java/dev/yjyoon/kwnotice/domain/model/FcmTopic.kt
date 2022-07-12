package dev.yjyoon.kwnotice.domain.model

enum class FcmTopic(val value: String) {
    KwHomeNew(value = TOPIC_KW_HOME_NEW),
    KwHomeEdit(value = TOPIC_KW_HOME_EDIT),
    SwCentralNew(value = TOPIC_SW_CENTRAL_NEW)
}

private const val TOPIC_KW_HOME_NEW = "kw-home-new"
private const val TOPIC_KW_HOME_EDIT = "kw-home-edit"
private const val TOPIC_SW_CENTRAL_NEW = "sw-central-new"
