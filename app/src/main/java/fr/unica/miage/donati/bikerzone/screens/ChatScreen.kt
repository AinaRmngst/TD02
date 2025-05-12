package fr.unica.miage.donati.bikerzone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.unica.miage.donati.bikerzone.viewmodel.ChatViewModel

@Composable
fun ChatScreen(
    navController: NavController,
    modifier: Modifier,
    chatViewModel: ChatViewModel = ChatViewModel(),
    onBack: () -> Unit,
    initialPrompt: String? = null,
) {
    val messages by chatViewModel.messages.collectAsState()
    var inputText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (!initialPrompt.isNullOrBlank() && messages.isEmpty()) {
            chatViewModel.startConversationWithAI(initialPrompt)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                Text(
                    text = "${message.sender} : ${message.text}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Écrivez un message...") }
            )
            IconButton(
                onClick = {
                    if (inputText.isNotBlank()) {
                        chatViewModel.sendMessage(inputText)
                        inputText = ""
                    }
                }
            ) {
                Icon(Icons.Default.Send, contentDescription = "Envoyer")
            }
        }
    }
}
