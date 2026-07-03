export type SoilInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type SoilResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
