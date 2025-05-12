package fr.unica.miage.donati.bikerzone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.unica.miage.donati.bikerzone.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChatMessage(val text: String, val sender: String)

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val generativeModel: GenerativeModel

    init {
        val apiKey = BuildConfig.GEMINI_API_KEY
        generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = apiKey
        )
    }

    fun sendMessage(userInput: String) {
        _messages.value += ChatMessage(userInput, "Vous")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(userInput)
                val reply = response.text ?: "Je n'ai pas compris."
                _messages.update { it + ChatMessage(reply, "IA") }
            } catch (e: Exception) {
                _messages.update { it + ChatMessage("Erreur : ${e.localizedMessage}", "IA") }
            }
        }
    }
}
