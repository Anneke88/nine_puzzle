#include <QCoreApplication>
#include <iostream>
#define N 3
using namespace std;
void print_puzzle(int * current);
bool move_index(int value, int *current);
int get_index(int value, int *current);
int compare_solution(int *final, int *current);
int main(int argc, char *argv[]) {
  int initial[9] = {0, 1, 2,
                    3, 4, 5,
                    6, 7, 8};
  int final[9] = {0, 1, 2,
                    3, 4, 5,
                    6, 7, 8};
  bool solved = false;
  // stay in loop until the puzzle is solved
  while ( !solved ) {
    print_puzzle(initial);
    int input = -1;
    // get input from use
    cin >> input;
    cout << get_index(input, initial) << endl;
    move_index(input, initial);
    if ( input == -1 ) {
      solved = true;
    }
    if ( compare_solution(final, initial) == 9) {
      cout << "Congrats, you completed the puzzle" << endl;
      solved = true;
    }
  }
  return 0;
}

void print_puzzle(int * current) {
  for ( int i = 0; i < N * N; i++) {
    cout << " " << current[i];
    if ( (i + 1) % N == 0) {
      cout << endl;
    }
  }
}

bool move_index(int value, int * current) {
  int lookup[9][4] = {
      {3, 1, -1, -1},
      {0, 4, 2, -1},
      {1, -1, 5, -1},
      {0, 4, 6, -1},
      {1, 3, 5, 7},
      {2, 4, 8, -1},
      {3, 0, 7, -1},
      {4, 6, 8, -1},
      {7, -1, 5, -1}
  };
  int zero_position = get_index(0, current);
  int value_position = get_index(value, current);
  for ( int i = 0; i < 4; i++) {
    if ( lookup[zero_position][i] == value_position ) {

      current[value_position] = 0;
      current[zero_position] = value;

      return true;
    }
  }
  return false;
}

int get_index(int value, int * current) {
  for ( int i = 0; i < N * N; i++ ) {
    if ( value == current[i] ) {
      return i;
    }
  }
  return 0;
}
int compare_solution(int *final, int *current) {
  for ( int i = 0; i < N * N; i++) {
    if ( final[i] != current[i] ) {
        return i + 1;
    }
  }
  return 9;
}
