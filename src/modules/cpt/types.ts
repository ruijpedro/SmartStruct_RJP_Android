export type CptInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type CptResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
