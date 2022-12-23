package com.vinodpatildev.musify.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.vinodpatildev.musify.data.entities.Song
import com.vinodpatildev.musify.other.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await

class MusicDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            val songs = songCollection.get().await().toObjects(Song::class.java).toMutableList()
            songs.shuffle()
            songs.toList()
        } catch(e: Exception) {
            emptyList()
        }
    }
}