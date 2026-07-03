export type GroundwaterInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type GroundwaterResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
