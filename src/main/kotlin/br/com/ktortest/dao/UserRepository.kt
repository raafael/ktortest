package br.com.ktortest.dao

import br.com.ktortest.model.Address
import br.com.ktortest.model.User
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository() {

    fun findAll() = transaction {
        UserTable.selectAll().map {
            User(it[UserTable.id].value,it[UserTable.name],it[UserTable.lastName])
        }
    }

    fun findAllWithAddress(): List<User> {
        val resultList : MutableList<User> = arrayListOf()

         transaction {
            (UserTable innerJoin AddressTable).selectAll().map {
                val address      = Address(it[AddressTable.id].value, it[AddressTable.street], it[AddressTable.zipCode], it[AddressTable.number])
                val indexElement = resultList.indexOfFirst {u -> u.id == it[UserTable.id].value}

                if(indexElement > -1) {
                    resultList[indexElement].addresses.add(address)
                } else {
                    val user = User(it[UserTable.id].value, it[UserTable.name], it[UserTable.lastName])
                    user.addresses.add(address)
                    resultList.add(user)
                }
            }
        }

        return resultList
    }
}