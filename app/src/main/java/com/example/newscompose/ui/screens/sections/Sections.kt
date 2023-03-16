package com.example.newscompose.ui.screens.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.example.newscompose.R


@Composable
fun SectionsScreen(
    onBackPressed:()->Unit,
    onSectionClick:(section:String)->Unit,
    modifier: Modifier = Modifier
){
    SectionsList(onSectionClick = onSectionClick)
}

@Composable
fun SectionsList(
    onSectionClick: (section: String) -> Unit,
    modifier: Modifier = Modifier
){
    val sections = stringArrayResource(id = R.array.sections)
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ){
        items(sections){section->
            SectionItem(section = section, onSectionClick = onSectionClick)
        }
    }
}

@Composable
fun SectionItem(
    onSectionClick: (section: String) -> Unit,
    section: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.clickable { onSectionClick(section) }
    ) {
        Text(
            modifier = modifier.padding(8.dp),
            text = section.lowercase().replaceFirstChar(Char::uppercase),
            style = MaterialTheme.typography.h3,

        )
        Divider(modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray, thickness = 1.dp)
    }
}

