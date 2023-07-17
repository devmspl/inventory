package com.app.inventory.Utlis

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class SocketConnection {
    companion object{

        fun convertStreamToString(`is`: InputStream): String? {
            val reader = BufferedReader(InputStreamReader(`is`))
            val sb = StringBuilder()
            var line: String? = null
            try {
                while (reader.readLine().also { line = it } != null) {
                    sb.append(line).append('\n')
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    `is`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return sb.toString()
        }

    }
}