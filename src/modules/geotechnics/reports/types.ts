export type ReportsInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type ReportsResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
