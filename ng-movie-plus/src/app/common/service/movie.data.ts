import { Observable, delay, filter, from, of, take } from 'rxjs';
import { Movie } from '../data/movie';
import { Injectable } from '@angular/core';

export const movies: Movie[] = [
  { id: 1, name: 'movie 1', description: 'description movie 1' },
  { id: 2, name: 'movie 2', description: 'description movie 2' },
  { id: 3, name: 'movie 3', description: 'description movie 3' },
  { id: 4, name: 'movie 4', description: 'description movie 4' },
];
