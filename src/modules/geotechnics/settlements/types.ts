export type SettlementsInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type SettlementsResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
