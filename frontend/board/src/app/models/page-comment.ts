import {Comment} from './comment';

export class PageComment {
  content: Comment[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number ;
  first: boolean ;
  sort: string ;
  numberOfElements: number ;
}
