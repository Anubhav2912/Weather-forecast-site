# ☁️ Weather Forecast Site

This repository hosts a web-based **Weather Forecasting Application** designed to display up-to-date weather forecasts. Built with a robust Java backend and a dynamic frontend framework, it leverages external APIs to provide accurate weather information.

---

### ✨ Features

* **Current Weather Display:** Shows real-time weather conditions.
* **Forecast Information:** Provides future weather predictions.
* **API Integration:** Fetches weather data from a reliable external source.
* **Responsive User Interface:** Built with a modern web framework for an interactive experience.

---

### 💻 Technologies Used

* **Backend:**
    * **Java 8:** The core programming language.
    * **Spring Boot:** Framework for building the stand-alone, production-ready backend application.
* **Frontend Framework:**
    * **Vaadin 13:** A popular framework for building rich web UIs in Java, allowing the entire application to be written in Java.
* **API Integration:**
    * **OpenWeatherMap API:** Used to retrieve weather data and forecasts.
* **Build Tool:**
    * **Maven:** For project build automation and dependency management (`pom.xml`).

---

### 📂 Project Structure

The project is structured as a standard Maven-based Spring Boot application with Vaadin:

* `src/`: Contains all the application's source code (Java, CSS, HTML for Vaadin components).
* `pom.xml`: The Maven Project Object Model file, defining project dependencies, build configurations, and plugin settings.
* `mvnw`, `mvnw.cmd`: Maven Wrapper scripts, allowing you to run Maven commands without a global Maven installation.
* `spring-boot-vaadin-weather-app.iml`: An IntelliJ IDEA module file, used by the IDE to configure the project.

---

### ⚙️ Setup and Run

To set up and run this Weather Forecast Site locally, you'll need **Java Development Kit (JDK) 8** or higher installed on your system.

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/Anubhav2912/Weather-forecast-site.git](https://github.com/Anubhav2912/Weather-forecast-site.git)
    cd Weather-forecast-site
    ```
2.  **Configure OpenWeatherMap API Key:**
    * You will need an API key from [OpenWeatherMap](https://openweathermap.org/api).
    * Add your API key to the application's configuration. This is typically done in `src/main/resources/application.properties` or `application.yml`, or as an environment variable. Look for a property like `openweathermap.api.key=YOUR_API_KEY_HERE` and replace the placeholder.

3.  **Build the Project:**
    Use Maven to compile the code and package the application:
    ```bash
    ./mvnw clean install
    ```
    (Use `mvnw.cmd clean install` on Windows)

4.  **Run the Application:**
    After a successful build, you can run the Spring Boot application:
    ```bash
    java -jar target/weather-forecast-site-<version>.jar
    ```
    (Replace `<version>` with the actual version number from your `pom.xml`, e.g., `1.0.0-SNAPSHOT`).

    Alternatively, you can run the application directly from your IDE (e.g., IntelliJ IDEA, Eclipse) by finding the main Spring Boot application class (usually annotated with `@SpringBootApplication`).

    Once the application starts, it will typically be accessible in your web browser at `http://localhost:8080` (or another port if configured).
