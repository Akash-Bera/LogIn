package com.example.loginpage

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.loginpage.ui.theme.LoginPageTheme
import java.nio.file.WatchEvent

/*var email = mutableStateOf("")
var password = mutableStateOf("")
var confirmPassword = mutableStateOf("")
var name = mutableStateOf("")*/
//var phoneNo = mutableStateOf("")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //Lottie()
                    CreateAccount()
                    //LoginPage1()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(avm: AccountViewModel = viewModel()){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    top = 20.dp,
                    end = 20.dp,
                    //bottom = 60.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp),
                    tint = Color(0xff003CBE)

                )
            }
            Text("Create Account",
                color = Color(0xff003CBE),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Create a new account",
                color = Color(0xff8D8D8D),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                //verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ShowName(avm)
                ShowEmail(avm)
                //ShowPhone(avm)
                ShowPassword(avm)
                ShowConfirmPassword(avm)
            }
        }
        Column(
            modifier = Modifier
                .padding(
                    bottom = 100.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CreateButton(avm, context)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(color = Color(0xff000000),
                            letterSpacing = 0.5.sp,
                            fontSize = 15.sp
                        )
                    ){
                        append("Already have a account?")
                    }
                    withStyle(
                        SpanStyle(Color(0xff003BBB),
                            letterSpacing = 0.5.sp,
                            fontSize = 15.sp)
                    ){
                        append("Login")
                    }
                }
            )
        }

    }
}


@Composable
fun ShowPlanText(avm: AccountViewModel) {
    Text(
        text = "name: ${avm.userName.value}",
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
    )
    Text(
        text = "email: ${avm.email.value}",
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
    Text(
        text = "password: ${avm.password.value}",
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
    Text(
        text = "confirmPass: ${avm.confirmPassword.value}",
        fontSize = 20.sp,
        modifier = Modifier.padding(10.dp)
    )
}


@Composable
fun CreateButton(avm:AccountViewModel,
                 context: Context
) {
    Button(onClick = {
        avm.Create()
        Toast.makeText(
            context,
            "${avm.userName.value} : ${avm.email.value} : ${avm.password.value}:${avm.confirmPassword.value}",
            Toast.LENGTH_SHORT
        ).show()
    }, enabled = avm.isEnabledCreateButton.value)
    {
        Text("CREATE ACCOUNT",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
    /*Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xff003CBE))
            .clickable {  },
        contentAlignment = Center
    ) {
        Text("CREATE ACCOUNT",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowConfirmPassword(avm: AccountViewModel) {
    OutlinedTextField(
        value = avm.confirmPassword.value,
        onValueChange = {
            avm.confirmPassword.value = it
            //avm.validateConfirmPassword()
        },
        isError = avm.isConfirmPasswordValid.value,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text("CONFIRM PASSWORD")
        },
        maxLines = 1,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_password),
                contentDescription = "",
                tint = Color(0xff15CDFF),
                modifier = Modifier
                    .size(20.dp)
            )
        }
    )
    Text(
        text = avm.confirmPasswordErrMsg.value,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(
                start = 8.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPassword(avm: AccountViewModel) {
    OutlinedTextField(
        value = avm.password.value,
        onValueChange = {
            avm.password.value = it
            avm.validatePassword()
        },
        isError = avm.isPasswordValid.value,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text("PASSWORD")
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_password),
                contentDescription = "",
                tint = Color(0xff15CDFF),
                modifier = Modifier
                    .size(20.dp)
            )
        }
    )
    Text(
        text = avm.passwordErrMsg.value,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(
                start = 8.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPhone(avm: AccountViewModel) {
    OutlinedTextField(
        value =avm.phoneNo.value,
        onValueChange = {
            avm.phoneNo.value = it
            avm.validatePhoneNo()
        },
        isError = avm.isPhoneNoValid.value,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text("PHONE")
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_phone),
                contentDescription = "",
                tint = Color(0xff15CDFF),
                modifier = Modifier
                    .size(20.dp)
            )
        }
    )
    Text(
        text = avm.phoneNoErrMsg.value,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(
                start = 8.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowEmail(avm: AccountViewModel) {
    OutlinedTextField(
        value = avm.email.value,
        onValueChange = {
            avm.email.value = it
            avm.validateEmail()
        },
        isError = avm.isEmailValid.value,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text("EMAIL")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Email,
                contentDescription = "",
                tint = Color(0xff15CDFF),
                modifier = Modifier
                    .size(20.dp)
            )
        },
        singleLine = true,
    )
    Text(
        text = avm.emailErrMsg.value,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(
                start = 8.dp
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowName(avm: AccountViewModel) {
    OutlinedTextField(
        value = avm.userName.value,
        onValueChange = {
            avm.userName.value = it
            avm.validateUserName()
        },
        isError = avm.isUserNameValid.value,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text("NAME")
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = "",
                tint = Color(0xff15CDFF),
                modifier = Modifier
                    .size(15.dp)
            )
        }
    )
    Text(
        text = avm.userNameErrMsg.value,
        color = Color.Red,
        fontSize = 14.sp,
        modifier = Modifier
            .padding(
                start = 8.dp
            )
    )
}


/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage1(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 30.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text("Welcome Back",
                color = Color(0XFF003BBB),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text("Sign to continue",
                color = Color(0xff8D8D8D)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text("Email")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Email,
                        contentDescription = "",
                        tint = Color(0xff15CDFF)
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange ={
                    password.value = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text("Password")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_password),
                        contentDescription = "",
                        tint = Color(0xff15CDFF)
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    *//*.padding(
                        end = 62.dp
                    )*//*,
                contentAlignment = Alignment.TopEnd
            ) {
                Text("Forgot Password?",
                    color = Color(0XFF003BBB),
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xff003BBB))
                ,
                contentAlignment = Alignment.Center
            ) {
                Text("LOGIN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 6.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(color = Color(0xff000000),
                            letterSpacing = 0.5.sp,
                                fontSize = 15.sp
                            )
                        ){
                            append("Don't have a account?")
                        }
                        withStyle(
                            SpanStyle(Color(0xff003BBB),
                                letterSpacing = 0.5.sp,
                                fontSize = 15.sp)
                        ){
                            append("create a new account")
                        }
                    }
                )
            }
        }


}*/

@Composable
fun Lottie(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))

    var isPlaying by remember { mutableStateOf(true) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )
    LaunchedEffect(key1 = progress){
        if(progress == 0f){
            isPlaying = true
        }
        if(progress == 1f){
            isPlaying = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieAnimation(
            modifier = Modifier
                .size(200.dp),
            composition = composition,
            progress = {progress}
        )

        Button(onClick = {
            isPlaying = true
        }) {
            Text("Play again")
        }
    }
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login",
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            placeholder = {
                Text("Email")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange ={
                password.value = it
            },
            placeholder = {
                Text("Password")
            },

        )
    }
}
*/

