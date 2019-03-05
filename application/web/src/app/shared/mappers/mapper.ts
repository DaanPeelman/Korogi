export interface IMapper<T> {
    map(resource: any): T;
    forType(): string;
}
