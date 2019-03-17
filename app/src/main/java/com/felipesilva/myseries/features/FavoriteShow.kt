package com.felipesilva.myseries.features

import android.content.Context
import java.io.*

class FavoriteShow {
    companion object {
        private val listFavorites = mutableSetOf<String>()

        private fun loadFavorites(context: Context) : MutableSet<String>{
            lateinit var objectReturn: MutableSet<String>

            val FILE_NAME = "favorite_list"
            val file : File = context.getFileStreamPath(FILE_NAME)

            if (file.exists()) {
                val fileInputStream = FileInputStream(file)
                val objectInputStream = ObjectInputStream(fileInputStream)

                if (listFavorites.isNotEmpty())
                    listFavorites.clear()

                objectReturn = objectInputStream.readObject() as MutableSet<String>

                listFavorites.addAll(objectReturn)

                fileInputStream.close()
                objectInputStream.close()
            }

            return listFavorites
        }

        fun getFavorites(context: Context) : MutableSet<String> {
            loadFavorites(context)
            return listFavorites
        }

        fun isFavorite(name: String) = listFavorites.filter { it.equals(name) }.any()

        fun addFavorite(name: String, context: Context) {
            val FILE_NAME = "favorite_list"
            val file : File = context.getFileStreamPath(FILE_NAME)

            val fileOutputStream = FileOutputStream(file)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            listFavorites.add(name)

            objectOutputStream.writeObject(listFavorites)
            objectOutputStream.close()
            fileOutputStream.close()
        }

        fun removeFavorite(name: String, context: Context) {
            val FILE_NAME = "favorite_list"
            val file : File = context.getFileStreamPath(FILE_NAME)

            val fileOutputStream = FileOutputStream(file)
            val objectOutputStream = ObjectOutputStream(fileOutputStream)

            listFavorites.remove(name)

            objectOutputStream.writeObject(listFavorites)
            objectOutputStream.close()
            fileOutputStream.close()
        }
    }
}