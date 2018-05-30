package br.com.ktortest.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

//class DataSource
fun intiDB() {
//    private val dataSource: HikariDataSource

//    constructor() {
        val cfg = HikariConfig("/datasource.properties")
//        cfg.addDataSourceProperty( "cachePrepStmts" , "true" )
//        cfg.addDataSourceProperty( "prepStmtCacheSize" , "250" )
//        cfg.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" )
//        dataSource = HikariDataSource(cfg)
        Database.connect(HikariDataSource(cfg))

//        logger.info("DB: Connected to $url")
//    }
}