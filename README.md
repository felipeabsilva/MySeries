# MySeries

URL Base: http://api.tvmaze.com/search/shows?q=rick
<br><br>
<b>Features</b>
<br>
Listar resultados da busca ✔
<br>
Campo para mudar o termo de pesquisa ✔
<br>
Favoritar programas ✔
<br><br>
<b>Dependencies adicionadas</b>
<br>
Retrofit 2.5.0
<br>
GSON Converter 2.5.0
<br>
CardView 28.0.0
<br>
RecyclerView 28.0.0
<br>
Glide 4.8.0

```
dependencies {
    def version_app_compat = "28.0.0"

    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    implementation "com.android.support:cardview-v7:$version_app_compat"
    implementation "com.android.support:recyclerview-v7:$version_app_compat"
    implementation "com.github.bumptech.glide:glide:4.8.0"
    annotationProcessor "com.github.bumptech.glide:compiler:4.8.0"

}
```
