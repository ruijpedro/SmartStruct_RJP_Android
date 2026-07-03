export type SptInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type SptResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
