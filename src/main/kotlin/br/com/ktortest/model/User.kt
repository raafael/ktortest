package br.com.ktortest.model

data class User(
        val id: Long,
        val name: String,
        val lastName: String
    )
{
    val addresses:ArrayList<Address>  = arrayListOf()
}