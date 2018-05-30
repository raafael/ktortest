package br.com.ktortest.dao

import org.jetbrains.exposed.dao.LongIdTable

object UserTable : LongIdTable() {
    val name = varchar("name", 25)
    val lastName = varchar("last_name", 25)
}