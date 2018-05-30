package br.com.ktortest.dao

import org.jetbrains.exposed.dao.LongIdTable

object AddressTable : LongIdTable() {
    val street = varchar("street", 100)
    val zipCode = varchar("zip_code", 25)
    val number = integer("number")
    val userId = optReference("fk_user", UserTable)
}