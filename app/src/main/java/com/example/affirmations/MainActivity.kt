/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme() {
                AffirmationAppPreview()
            }
        }
    }
}

// Step *1* - The AffirmationApp() is the prime function that calls the AffirmationList() by using
// the DatasourceClass's loadAffirmation function(which returns a list of Int values for image and
// string resources)
@Composable
fun AffirmationApp() {
    AffirmationsTheme {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
    }
}


// Step *2* - The AffirmationList() receives a list of Affirmation items(List<Affirmation>) from the
// AffirmationApp(), which then sets the view to LazyColumn
@Composable
private fun AffirmationList(
    affirmationList: List<Affirmation>, modifier: Modifier = Modifier
) {
    Column() {

        // The text will stick to the Top
        Text(
            text = "LazyColumn App",
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(16.dp)
        )
        // Lazy List
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            //[items()] is kinda similar to  forEach function
            // for each affirmations(item in the List,
            // call the Affirmation Card with Image and String Value)
            items(affirmationList) { affirmation ->
                AffirmationCard(affirmation = affirmation)
            }
        }
    }
}

// Step *3* - The Affirmation Card receives an Affirmation Item, which contains an Image and a string
// resource, then it sets both resources as defined
@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

// Creating a preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AffirmationAppPreview() {
    AffirmationsTheme {
        AffirmationList(affirmationList = Datasource().loadAffirmations())
// Trial --  AffirmationCard(Affirmation(R.drawable.image1, R.string.affirmation1))
    }
}