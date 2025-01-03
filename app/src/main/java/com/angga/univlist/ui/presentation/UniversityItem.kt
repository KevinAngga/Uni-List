package com.angga.univlist.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.angga.univlist.R
import com.angga.univlist.domain.model.University
import com.angga.univlist.domain.utils.extractDomainNames

@Composable
fun UniversityItem(
    university: University,
    onItemClick : (url : String) -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = university.uniName,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = university.countryCode,
            style = MaterialTheme.typography.bodyMedium,
        )

        university.webPages.forEach {
            Text(
                modifier = Modifier.clickable {
                    onItemClick(it)
                },
                text = stringResource(R.string.website, extractDomainNames(it)),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}