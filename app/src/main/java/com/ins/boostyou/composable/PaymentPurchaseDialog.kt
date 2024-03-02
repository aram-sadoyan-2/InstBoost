package com.ins.boostyou.composable

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat.startActivity
import com.ins.boostyou.utils.findActivity
import com.ins.boostyou.viewModel.InAppPurchaseViewModel


@Composable
fun PaymentPurchaseDialog(
    inAppPurchaseViewModel: InAppPurchaseViewModel,
    popupTitle: String? = null,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 28.dp)
                    .padding(top = 26.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                inAppPurchaseViewModel.packageDetails?.values?.toMutableList()
                    ?.let { packageDetails ->
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(packageDetails) { packageDetail ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(34.dp))
                                        .background(Color(0xFFF06B78))
                                        .clickable {
                                            inAppPurchaseViewModel.launchInAppBillingFlow(
                                                activity,
                                                packageDetail.packageId
                                            )
                                        }
                                ) {
                                    val modifier =
                                        Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                                    Text(
                                        text = packageDetail.name,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        modifier = modifier
                                    )
                                    Text(
                                        modifier = modifier.weight(1f, fill = false),
                                        text = packageDetail.price,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                    )
                                }
                            }
                        }
                        ClickableText(
                            text = annotatedString,
                            modifier = Modifier.padding(start = 2.dp, top = 12.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            onClick = { offset ->
                                annotatedString.getStringAnnotations(
                                    tag = "policy",
                                    start = offset,
                                    end = offset
                                ).firstOrNull()?.let {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://boostyou.convocraftapp.com/privacy/?fbclid=IwAR1-P25P2zrElBlC1x9iyvsFHwQjlVgJ-lqQxDmUV-HCP3E7t3tnSFnUVdw")
                                    )
                                    activity.startActivity(intent)
                                }
//                                annotatedString.getStringAnnotations(
//                                    tag = "terms",
//                                    start = offset,
//                                    end = offset
//                                ).firstOrNull()?.let {
//                                    Log.d("terms URL", it.item)
//                                }
                            })
                    }
            }
        }
    }
}

val annotatedString = buildAnnotatedString {
   // append("Privacy Policy ")
    pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
    withStyle(style = SpanStyle(color = Color.Blue)) {
        append("Privacy Policy")
    }
//    pop()
//    append(" and ")
//    pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")
//    withStyle(style = SpanStyle(color = Color.Blue)) {
//        append("terms of use")
//    }
//    pop()
}