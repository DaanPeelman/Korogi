export class EmbeddedResource<T> {
  constructor(
    public content: T,
    public embedded: { [key:string]: any; },
    public links: { [key:string]: string; }
  ) {
    this.embedded = embedded;
  }
}
