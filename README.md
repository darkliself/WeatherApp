# Weather App

A simple Android application that provides weather information by fetching data from the [OpenWeatherMap API](https://openweathermap.org/api). The app shows the current weather, along with forecasts for the upcoming days. Built using modern Android technologies like Jetpack Compose and Hilt, the app also leverages Retrofit for network requests and DataStore for local storage.

## Libraries Used

- **Jetpack Compose**: For building a modern, declarative UI.
- **Retrofit**: For network requests to the OpenWeatherMap API.
- **Gson Converter**: For converting JSON responses into Kotlin objects.
- **Hilt**: For dependency injection.
- **Moshi**: For JSON serialization and deserialization.
- **DataStore**: For storing app preferences locally.
- **Lifecycle**: To manage app lifecycle and data binding efficiently.

## Architecture

This app follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model**: Represents the data from the API (weather information).
- **View**: Composed of UI elements built using Jetpack Compose.
- **ViewModel**: Acts as a middleman, interacting with the Model and providing data to the View.

## Installation

Clone the repository to your local machine:

```bash
git clone https://github.com/darkliself/WeatherApp.git
