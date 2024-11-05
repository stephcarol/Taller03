class GymMembership:
    def __init__(self, membership_type, base_cost):
        self.membership_type = membership_type
        self.base_cost = base_cost
        self.features = []
        self.total_cost = base_cost

    def add_feature(self, feature, cost):
        self.features.append((feature, cost))
        self.total_cost += cost

    def calculate_total_cost(self, group_discount=False, premium=False):
        # Aplicar descuento de grupo si es necesario
        if group_discount:
            self.total_cost *= 0.9  # 10% de descuento

        # Aplicar sobrecosto de características premium si es necesario
        if premium:
            self.total_cost *= 1.15  # 15% de sobrecosto

        # Descuento especial basado en el costo total
        if self.total_cost > 400:
            self.total_cost -= 50
        elif self.total_cost > 200:
            self.total_cost -= 20

        return round(self.total_cost, 2)

def display_membership_options():
    memberships = {
        "Basic": 50,
        "Premium": 100,
        "Family": 150
    }
    print("Planes de Membresía:")
    for name, cost in memberships.items():
        print(f"{name} - ${cost}")
    return memberships

def display_features():
    features = {
        "Personal Training": 30,
        "Group Classes": 20,
        "Spa Access": 40
    }
    print("Características adicionales:")
    for name, cost in features.items():
        print(f"{name} - ${cost}")
    return features

def select_membership(memberships):
    choice = input("Seleccione un plan de membresía: ")
    if choice in memberships:
        return choice, memberships[choice]
    else:
        print("Selección no válida. Intente de nuevo.")
        return None, None

def select_features(features):
    selected_features = []
    while True:
        choice = input("Seleccione una característica adicional (o 'done' para terminar): ")
        if choice.lower() == 'done':
            break
        elif choice in features:
            selected_features.append((choice, features[choice]))
        else:
            print("Selección no válida. Intente de nuevo.")
    return selected_features

def main():
    memberships = display_membership_options()
    features = display_features()

    membership_type, base_cost = select_membership(memberships)
    if membership_type is None:
        print("Error en la selección de membresía.")
        return -1

    membership = GymMembership(membership_type, base_cost)
    selected_features = select_features(features)
    for feature, cost in selected_features:
        membership.add_feature(feature, cost)

    group_discount = input("¿Es una membresía de grupo? (s/n): ").lower() == 's'
    premium = input("¿Incluye características premium? (s/n): ").lower() == 's'

    total_cost = membership.calculate_total_cost(group_discount=group_discount, premium=premium)

    print("\nResumen de la Membresía:")
    print(f"Plan seleccionado: {membership_type}")
    print("Características adicionales:", ", ".join([f[0] for f in membership.features]))
    print(f"Costo total: ${total_cost}")

    confirmation = input("¿Desea confirmar esta membresía? (s/n): ").lower()
    if confirmation == 's':
        print("¡Membresía confirmada!")
        return total_cost
    else:
        print("Membresía cancelada.")
        return -1

if __name__ == "__main__":
    main()
