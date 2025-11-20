package com.example.cse227etp

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
//for firebase notification I had to create this and also added service in manifest file
//And had to add dependency in build.gradle file named as firebase-messaging
// added version, and library too for this named as firebaseMessaging and firebase-messaging respectively
//Added notification permission and service for the class MyFirebaseMessagingService to work in manifest file
//Then after syncing had to go to build and there Clicked on Generate App Bundles or Apks clicked on generate apk
//then C:\Users\Priya\AndroidStudioProjects\FirebaseAuthentication\app\build\outputs\apk\debug in this i got apk file which i shared on whatsapp in order to download on phone
//then after installing the apk gave permission of notifications to application
//Opened firebase console there clicked on messaging created campaign where notification is created
//after few mins the active status will get completed and it will show notification on phone

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("FCM", "Message received: ${remoteMessage.notification?.body}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New token: $token")
        // Send token to your server if needed
    }
}