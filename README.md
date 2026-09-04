# Memory Game

[![CI](https://github.com/fatmakahveci/MemoryGame/actions/workflows/ci.yml/badge.svg)](https://github.com/fatmakahveci/MemoryGame/actions/workflows/ci.yml)
[![Release](https://img.shields.io/github/v/release/fatmakahveci/MemoryGame?display_name=tag&sort=semver)](https://github.com/fatmakahveci/MemoryGame/releases)
[![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Coverage](https://img.shields.io/badge/coverage-JaCoCo-CB2029)](https://www.jacoco.org/jacoco/)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](LICENSE.md)

A terminal-based card-matching game built to demonstrate testable Java design. Domain rules are isolated from console input and output, so gameplay can be exercised deterministically without manual interaction.

## Demo

![Animated terminal walkthrough of Memory Game](demo.gif)

## Features

- Configurable board dimensions and turn-based matching
- Input validation for board positions and repeated selections
- Clear separation between game rules, board state, and terminal UI
- Injectable input abstraction for deterministic tests
- Unit coverage for the engine and core value objects
- JaCoCo reports and a 60% instruction-coverage quality gate

## Design

```text
ConsoleUI + ScannerInput
          │
          ▼
      GameEngine
       │      │
       ▼      ▼
     Board   Player
       │
       ▼
 Cell + Position + TurnResult
```

`GameEngine` coordinates turns and scoring. `Board` owns card state and matching rules. The `Input` interface keeps terminal I/O outside the domain layer and can be replaced with a test double.

## Requirements

- JDK 25 or newer
- Maven 3.9 or newer

## Run Locally

```bash
git clone https://github.com/fatmakahveci/MemoryGame.git
cd MemoryGame
mvn clean package
java -jar target/memorygame-1.0-SNAPSHOT.jar
```

Follow the terminal prompts to choose board dimensions and select cards by row and column.

## Testing and Coverage

```bash
mvn clean verify
```

The command runs the JUnit and Mockito suite, applies the coverage gate, and writes the HTML report to `target/site/jacoco/index.html`.

## Project Structure

```text
src/
├── main/java/com/fatmakahveci/memorygame/   Application and domain code
└── test/java/com/fatmakahveci/memorygame/   Unit tests
```

## Extension Ideas

- Timed and difficulty-based modes
- Persistent scores and a leaderboard
- Multiplayer rules
- Swing, JavaFX, or web-based presentation layers

## Contributing

Read the [contributing guide](.github/CONTRIBUTING.md). Keep domain logic independent of console I/O and include tests for behavioral changes.

## Project Resources

- [Releases](https://github.com/fatmakahveci/MemoryGame/releases)
- [Changelog](CHANGELOG.md)
- [Security policy](.github/SECURITY.md)
- [License](LICENSE.md)
