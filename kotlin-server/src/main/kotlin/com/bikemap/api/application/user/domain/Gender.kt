package com.bikemap.api.application.user.domain

/**
 * Gender 객체
 */
enum class Gender(val title: String) {
    MALE("남성"),
    FEMALE("여성");

    companion object {
        private val lookup = HashMap<String, Gender>()

        init {
            Gender.values().map { lookup.put(it.title, it) }
        }

        fun getFromValue(title: String): Gender {
            return lookup[title]!!
        }
    }
}
