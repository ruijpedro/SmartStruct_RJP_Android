export type RetainingwallsInput = {
  name: string;
  gamma: number;
  phi: number;
  cohesion: number;
};

export type RetainingwallsResult = {
  ok: boolean;
  factor: number;
  notes: string[];
};
