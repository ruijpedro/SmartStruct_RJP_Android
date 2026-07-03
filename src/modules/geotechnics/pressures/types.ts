export type PressuresInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type PressuresResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
