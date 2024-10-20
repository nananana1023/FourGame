# FourGame

## Description

Four Game is a two-player game played on an n x n board, where each field contains a value between 0 and 4. Initially, all fields are set to 0. Players take turns choosing a field, which increments the value of that field and its neighbors by 1 (if the value is less than 4). The goal is to fill fields to a value of 4, with the player scoring points based on how many fields they have colored.

The game ends when all fields have reached a value of 4, and the player with the higher score wins.

## Features

- Selectable board sizes: 3x3, 5x5, or 7x7.
- Automatically detects the end of the game and announces the winner.
- Each field can be colored red or blue based on the player's color once it reaches 4.

## How to Play

1. Choose a board size (3x3, 5x5, or 7x7).
2. Players take turns selecting a field on the board.
3. Watch the values of the selected field and its neighbors increment.
4. Score points by filling fields to a value of 4.
5. The game ends when all fields reach a value of 4. The player with the higher score wins.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/nananana1023/FourGame.git
