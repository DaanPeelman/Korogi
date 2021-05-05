import { ParamMap } from "@angular/router";

export class ParamMapImpl implements ParamMap {
    readonly keys: string[];

    constructor() {
        this.keys = [];
    }

    get(name: string): string | null {
        return this.keys[name];
    }

    getAll(name: string): string[] {
        return this.keys[name];
    }

    has(name: string): boolean {
        return this.keys[name] !== undefined && this.keys[name] !== null;
    }

    add(
        name: string,
        value: any
    ) {
        this.keys[name] = value;
    }
}
