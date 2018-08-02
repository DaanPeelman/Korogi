import { Link } from "../link";

export class EnrichedResource<T> {
  public embedded: { [key: string]: any } = [];

  constructor(
    public data: T,
    public links: Link[]
  ) {}
}
