import { Movie } from "./movie";

export interface MovieDetail extends Movie{
    description : string;
    banner : {bannerUrl : string}[];
}
  