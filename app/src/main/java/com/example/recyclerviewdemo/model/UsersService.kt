package com.example.recyclerviewdemo.model

import com.github.javafaker.Faker
import java.util.*
import kotlin.collections.ArrayList

typealias UsersListener = (users: List<User>) -> Unit

class UsersService {

    private var users = mutableListOf<User>()
    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        users = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size]
            )
        }. toMutableList()
    }

    fun getUsers(): List<User> {
        return users
    }

    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id}
        if (indexToDelete != -1) {
            users = ArrayList(users)
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun fireUser(user: User) {
        val index = users.indexOfFirst { it.id == user.id}
        if (index == -1) return
        val updatedUser = users[index].copy(company = "")
        users = ArrayList(users)
        users[index] = updatedUser
        notifyChanges()
    }

    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }

    companion object {

        private val IMAGES = mutableListOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTg1CdUxS1atCyGvAa4MxpgKcCLgBEUKsUUnw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkNClHcBngc-qOfx6OQ-rCm6L-xHp-t6R2QA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMp5pHncrwUo1ITvpSCKIORJxMnTqCZ3PMLw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7TjyxXhIkwiNE1996F349Lr8xRDEyQ7r6cA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5faVszR5XQKwYrgak66d8QDijo5m739aLfQ&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSo7S86HLw3FSPP3Iflpfq1OkfEfkB8zdRGpw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmyahTs5AsNQyQtP56yqmHmX3FlFHM6fU7Vg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROMOduz51EAOEtJzWsaHONJq-qdnDwFjBwlg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_EUnzR3G_qsayMQv5g5blq2ntAoRspEFMnA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJYhkvL28a_Ro2jtf7-s6bU1tvuhfHCp62YA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQa12HvZ05vsjk80mgqKj_dle7dEWU9upkamA&usqp=CAU"
        )

    }

}