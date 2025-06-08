package  com.cesitar.appdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

import com.cesitar.appdelivery.ui.theme.AppDeliveryTheme

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDeliveryTheme {
                Scaffold(Modifier.fillMaxSize()){ padding ->
                    Text("jfw")
                }
            }
        }
    }
}