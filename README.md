# Memory Game

[![CI](https://github.com/fatmakahveci/MemoryGame/actions/workflows/ci.yml/badge.svg)](https://github.com/fatmakahveci/MemoryGame/actions/workflows/ci.yml)
[![Release](https://img.shields.io/github/v/release/fatmakahveci/MemoryGame?display_name=tag&sort=semver)](https://github.com/fatmakahveci/MemoryGame/releases)
[![Java](https://img.shields.io/badge/Java-Maven-ED8B00?logo=openjdk&logoColor=white)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue.svg)](LICENSE.md)

A terminal-based memory card matching game designed as an exercise in testable architecture and separation of concerns.

Unlike typical console games, the game logic is completely independent from user input and output, allowing the core behaviour to be unit tested automatically.

## Key Idea

Most beginner console applications directly read from Scanner, which tightly couples the UI to the game rules.

In this project:
- the game engine contains no console code
- user input is abstracted behind an interface
- UI is replaceable
- the core behaviour can be tested deterministically

This mirrors real backend service design, where side effects must be isolated.

## Demo

![demo](demo.gif)

---

## Why this project exists

Many beginner Java projects tightly couple game logic with user input, which makes testing difficult.

In this project:

- the game rules are independent from the console
- input is abstracted behind an interface
- the board logic is unit tested
- user interaction can be mocked in tests

This allows the program to be tested without manual interaction.

---

## Features

- turn-based memory card matching game
- configurable board size
- clean separation between UI and game logic
- deterministic unit tests using mocked input
- error handling for invalid positions

---

## Architecture

The project separates responsibilities into distinct layers:

- **GameEngine** → game rules, scoring, turn handling
- **Board** → board state and matching logic
- **ConsoleUI** → user interaction and rendering
- **Input abstraction** → allows mocking user interaction in tests
- **Position** → immutable coordinates
- **ScannerInput** → console implementation

This design allows the core game logic to be tested without any console input.

---

## Tech Stack

- JavaScript (Vanilla)
- HTML5
- CSS3
- Maven
- JUnit
- JaCoCo (coverage)
- GitHub Actions (CI)

---

## Project structure

```
memorygame
├── Board.java        -> board state & matching logic
├── Cell.java         -> individual card representation
├── Position.java     -> immutable board position
├── MemoryGame.java   -> game flow / rules
├── Input.java        -> input abstraction
└── ScannerInput.java -> console implementation
```

The `Input` interface allows the game to run without a real user during tests.

---

## Requirements

- Java 25+
- Maven

---

## Run locally

Clone the repository:

```bash
git clone https://github.com/fatmakahveci/MemoryGame.git
cd MemoryGame
```

Build:

```bash
mvn package
```

Run:

```bash
java -jar target/memorygame-1.0-SNAPSHOT.jar
```

---

## Running tests

```bash
mvn test
```

Tests simulate player actions using mocked input and verify the game rules automatically.

Coverage reports are generated with JaCoCo on every push via CI.

---

## Example Usage

Start the game locally and follow the prompts to select rows and columns for each move:

```bash
mvn package
java -jar target/memorygame-1.0-SNAPSHOT.jar
```

The game will prompt for board dimensions and then ask players to pick cells by row and column.

---

## Contributing

Contributions are welcome!

- Fork the repository and create a new branch for your changes.
- Add tests for any behavior you change or add.
- Keep game logic separate from UI behavior where possible.
- Open a pull request with a short description of the improvement.

---

## Design notes

Instead of reading directly from Scanner inside the game logic, an Input interface is injected into the game.

This enables:
- automated testing
- reproducible edge cases
- isolation of side effects
- replaceable UI (console → GUI)

This pattern is equivalent to dependency injection used in backend services.

---

## Design notes

- The `Input` interface isolates console I/O from game rules.
- Game logic is kept free of side effects, enabling deterministic unit tests.
- UI implementation is replaceable without changing engine behavior.

---

## Possible improvements

- scoring system
- multiplayer support
- GUI (Swing/JavaFX)
- persistent high scores
- timer, difficulty levels and leaderboard
- store scores in a file or database
- add an AI opponent

---

## License

Apache License 2.0

---

✅ Coverage report is generated with JaCoCo and uploaded as a GitHub Actions artifact on every push.

---

## What I learned

This project focuses on writing testable code rather than just making the game work. It focuses on front-end logic rather than visuals.

Key takeaways:
- managing UI state
- handling user events
- preventing race conditions (double click issues)
- organizing client-side logic
- designing for testability
- separating domain logic from infrastructure
- mocking side effects
- writing deterministic unit tests
- structuring a maintainable codebase

---

Contributions, suggestions and improvements are welcome.

---
