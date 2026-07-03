export type SlopesInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type SlopesResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
