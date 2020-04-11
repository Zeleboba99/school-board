import {Post} from './post';

export class PagePost {
  content: Post[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number ;
  first: boolean ;
  sort: string ;
  numberOfElements: number ;
}
