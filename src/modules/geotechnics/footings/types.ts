export type FootingsInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type FootingsResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
