export type ExamplesInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type ExamplesResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
