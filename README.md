![CI](https://github.com/fatmakahveci/MemoryGame/actions/workflows/ci.yml/badge.svg)

# Memory Game

Terminal memory card game in Java focused on testable design and clean architecture.

This project is not just a game — it is a small exercise in writing **testable and modular software**.
The goal was to separate game rules, input/output, and board logic so the core behaviour can be tested independently from the console.

---

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

- Java 21+
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

The tests use mocked input to simulate player actions and verify game behaviour.

---

## Design notes

The main design decision was introducing an `Input` interface.

Instead of reading directly from `Scanner` inside the game logic, user interaction is injected into the game.
This makes it possible to test the game automatically and verify edge cases such as invalid input or repeated moves.

This pattern is similar to dependency injection and is commonly used in backend services to isolate side effects.

---

## Possible improvements

- scoring system
- multiple players
- GUI (Swing/JavaFX)
- persistent high scores

---

## License

Apache License 2.0

---

✅ Coverage report is generated with JaCoCo and uploaded as a GitHub Actions artifact on every push.

---

Contributions, suggestions and improvements are welcome.

---
