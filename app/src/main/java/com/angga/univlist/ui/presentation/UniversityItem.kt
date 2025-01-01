package com.angga.univlist.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angga.univlist.ui.domain.model.University

@Composable
fun UniversityItem(
    university: University,
    onItemClick : (url : String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = university.uniName
        )

        Text(
            text = university.countryCode
        )

        university.webPages.forEach {
            Text(
                modifier = Modifier.clickable {
                    onItemClick(it)
                },
                text = it
            )
        }
    }
}