package br.com.ktortest.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

//class DataSource
fun intiDB() {

        val cfg = HikariConfig("/datasource.properties")
        Database.connect(HikariDataSource(cfg))
}